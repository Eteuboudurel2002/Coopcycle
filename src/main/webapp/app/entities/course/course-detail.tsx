import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './course.reducer';

export const CourseDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const courseEntity = useAppSelector(state => state.course.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="courseDetailsHeading">
          <Translate contentKey="coopcycleApp.course.detail.title">Course</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{courseEntity.id}</dd>
          <dt>
            <span id="echeance">
              <Translate contentKey="coopcycleApp.course.echeance">Echeance</Translate>
            </span>
            <UncontrolledTooltip target="echeance">
              <Translate contentKey="coopcycleApp.course.help.echeance" />
            </UncontrolledTooltip>
          </dt>
          <dd>{courseEntity.echeance}</dd>
          <dt>
            <span id="dateCreation">
              <Translate contentKey="coopcycleApp.course.dateCreation">Date Creation</Translate>
            </span>
          </dt>
          <dd>
            {courseEntity.dateCreation ? <TextFormat value={courseEntity.dateCreation} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="statut">
              <Translate contentKey="coopcycleApp.course.statut">Statut</Translate>
            </span>
          </dt>
          <dd>{courseEntity.statut}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.course.idPanier">Id Panier</Translate>
          </dt>
          <dd>{courseEntity.idPanier ? courseEntity.idPanier.id : ''}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.course.idCoursier">Id Coursier</Translate>
          </dt>
          <dd>{courseEntity.idCoursier ? courseEntity.idCoursier.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/course" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/course/${courseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CourseDetail;
